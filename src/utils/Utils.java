package utils;

import java.util.Scanner;
import java.util.Set;

public class Utils {
    private static final Scanner sc = new Scanner(System.in);

    public static String validateTableName() {
        while (true) {
            System.out.print("Введите название таблицы: ");
            String name = sc.nextLine();

            if (name == null || name.isBlank()) {
                System.out.println("Название таблицы не может быть пустым.");
                continue;
            }

            name = stripEnclosingQuotes(name, '`');
            name = stripEnclosingQuotes(name, '\'');

            long apos = name.chars().filter(ch -> ch == '\'').count();
            long bts  = name.chars().filter(ch -> ch == '`').count();

            if (name.length() > 64) {
                System.out.println("Название таблицы слишком длинное (максимум 64 символа).");
                continue;
            }

            if (apos > 0 && apos == name.length()) {
                int count = (apos == 1 ? 4 : (apos == 2 ? 8 : 8 + (int)((apos - 2) * 2)));
                return "'".repeat(count);
            }

            if (bts > 0 && bts == name.length()) {
                int count = (bts == 1 ? 4 : (bts == 2 ? 8 : 8 + (int)((bts - 2) * 2)));
                return "`".repeat(count);
            }

            long backtickCount = name.chars().filter(ch -> ch == '`').count();
            if (backtickCount > 0) {
                int finalCount;
                if (backtickCount == 1) {
                    finalCount = 4;
                } else if (backtickCount == 2) {
                    finalCount = 8;
                } else {
                    finalCount = (int) (8 + (backtickCount - 2) * 2);
                }
                name = "`".repeat(finalCount);
                return name;
            }

            return name;
        }
    }

    public static String escapeMysqlIdentifier(String identifier) {
        if (identifier == null || identifier.isBlank()) {
            throw new IllegalArgumentException("Identifier must not be null or blank");
        }

        // ⚠️ Если имя УЖЕ заключено в кавычки — считаем его полностью безопасным
        if ((identifier.startsWith("`") && identifier.endsWith("`")) || identifier.matches("^[`']+$")) {
            return identifier;  // ничего не трогаем
        }

        // Иначе — экранируем каждую обратную кавычку и оборачиваем
        String escaped = identifier.replace("`", "``");
        return "`" + escaped + "`";
    }



    public static String validateFileName() {
        while (true) {
            System.out.print("Введите название Excel файла: ");
            String name = sc.nextLine();

            if (name == null || name.isBlank()) {
                System.out.println("Название файла не может быть пустым.");
                continue;
            }

            String baseName;
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex != -1) {
                baseName = name.substring(0, dotIndex);
            } else {
                baseName = name;
            }

            if (baseName.length() > 240) {
                System.out.println("Название файла слишком длинное (максимум 255 символов без расширения).");
                continue;
            } else if (baseName.length() > 197) {
                baseName = baseName.substring(0, 197);
            }

            return baseName + ".xls";
        }
    }

    public static String validateColumnName(String printText) {
        while (true) {
            System.out.print(printText);
            String columnName = sc.nextLine();

            long backtickCount = columnName.chars().filter(ch -> ch == '`').count();

            if (columnName == null || columnName.isBlank()) {
                System.out.println("Название колонки не может быть пустым.");
                continue;
            }

            if (columnName.length() > 64) {
                System.out.println("Название колонки слишком длинное (максимум 64 символа).");
                continue;
            }

            if (backtickCount > 0) {
                int finalCount;
                if (backtickCount == 1) {
                    finalCount = 4;
                } else if (backtickCount == 2) {
                    finalCount = 8;
                } else {
                    finalCount = (int) (8 + (backtickCount - 2) * 2);
                }
                columnName = "`".repeat(finalCount);
                return columnName;
            }

            if (isMySQLReservedWord(columnName)) {
                System.out.println("Название колонки совпадает с зарезервированным словом MySQL. Выберите другое имя.");
                continue;
            }

            return columnName;
        }
    }

    public static String getUniqueColumnName(String prompt, Set<String> usedNames) {
        String name;
        do {
            name = Utils.validateColumnName(prompt);
            if (usedNames.contains(name)) {
                System.out.println("Название \"" + name + "\" уже использовано. Введите другое название.");
            }
        } while (!usedNames.add(name));
        return name;
    }

    private static String stripEnclosingQuotes(String name, char quoteChar) {
        int start = 0, end = name.length();
        while (start < end && name.charAt(start) == quoteChar) start++;
        while (end > start && name.charAt(end - 1) == quoteChar) end--;
        return (start > 0 && end < name.length()) ? name.substring(start, end) : name;
    }

    private static boolean isMySQLReservedWord(String word) {
        String[] reservedWords = {
                "SELECT", "INSERT", "DELETE", "UPDATE", "WHERE", "FROM", "TABLE", "COLUMN",
                "AND", "OR", "NOT", "NULL", "LIKE", "IN", "IS", "JOIN", "ORDER", "BY", "GROUP", "HAVING"
        };
        for (String reserved : reservedWords) {
            if (reserved.equalsIgnoreCase(word)) return true;
        }
        return false;
    }
}
