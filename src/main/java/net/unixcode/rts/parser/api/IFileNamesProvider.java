package net.unixcode.rts.parser.api;

import java.util.List;
import java.util.function.Function;

/**
 * Определяет механизм извлечения из командной строки списка файлов для обработки
 */
@FunctionalInterface
public interface IFileNamesProvider extends Function<List<String>, List<String>> {
}
