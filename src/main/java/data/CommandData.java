package data;

public enum CommandData {
  ADD,
  LIST,
  UPDATE,
  FILTER,
  EXIT;

  public static CommandData fromString(String command) {
    try {
      return CommandData.valueOf(command.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
