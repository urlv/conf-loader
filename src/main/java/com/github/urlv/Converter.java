package com.github.urlv;

class Converter {
    public static Object convert(String val, Class<?> type) {
        try {
            switch (type.getSimpleName().toLowerCase()) {
                case "boolean":
                    return Boolean.parseBoolean(val);
                case "int":
                case "integer":
                    return Integer.parseInt(val);
                case "long":
                    return Long.parseLong(val);
                case "float":
                    return Float.parseFloat(val);
                case "double":
                    return Double.parseDouble(val);
                case "string":
                    return val;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
