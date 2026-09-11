package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Utility class for JSON read/write operations.
 *
 * Uses Jackson ObjectMapper.
 */
public final class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

	private JsonUtils() {
		// Prevent object creation
	}

	/**
	 * Creates configured ObjectMapper.
	 *
	 * @return ObjectMapper
	 */
	private static ObjectMapper createObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();

		mapper.findAndRegisterModules();

		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		return mapper;
	}

	/**
	 * Reads JSON file and converts it to Java object.
	 *
	 * @param filePath JSON file path
	 * @param clazz    target class
	 * @param <T>      target type
	 * @return Java object
	 */
	public static <T> T read(String filePath, Class<T> clazz) {

		try {

			return OBJECT_MAPPER.readValue(new File(filePath), clazz);

		} catch (IOException e) {

			throw new RuntimeException("Unable to read JSON file: " + filePath, e);
		}
	}

	/**
	 * Reads JSON resource from classpath.
	 *
	 * Example:
	 *
	 * testdata/users.json
	 *
	 * @param resourcePath classpath resource
	 * @param clazz        target class
	 * @param <T>          target type
	 * @return Java object
	 */
	public static <T> T readResource(String resourcePath, Class<T> clazz) {

		try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (inputStream == null) {

				throw new RuntimeException("JSON resource not found: " + resourcePath);
			}

			return OBJECT_MAPPER.readValue(inputStream, clazz);

		} catch (IOException e) {

			throw new RuntimeException("Unable to read JSON resource: " + resourcePath, e);
		}
	}

	/**
	 * Reads JSON array into List.
	 *
	 * @param filePath JSON file
	 * @param clazz    list element type
	 * @param <T>      element type
	 * @return list
	 */
	public static <T> List<T> readList(String filePath, Class<T> clazz) {

		try {

			JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);

			return OBJECT_MAPPER.readValue(new File(filePath), type);

		} catch (IOException e) {

			throw new RuntimeException("Unable to read JSON list: " + filePath, e);
		}
	}

	/**
	 * Reads JSON resource array into List.
	 *
	 * @param resourcePath resource path
	 * @param clazz        element type
	 * @param <T>          element type
	 * @return list
	 */
	public static <T> List<T> readResourceList(String resourcePath, Class<T> clazz) {

		try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (inputStream == null) {

				throw new RuntimeException("JSON resource not found: " + resourcePath);
			}

			JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);

			return OBJECT_MAPPER.readValue(inputStream, type);

		} catch (IOException e) {

			throw new RuntimeException("Unable to read JSON resource list: " + resourcePath, e);
		}
	}

	/**
	 * Converts Java object to JSON string.
	 *
	 * @param object Java object
	 * @return JSON string
	 */
	public static String toJson(Object object) {

		try {

			return OBJECT_MAPPER.writeValueAsString(object);

		} catch (JsonProcessingException e) {

			throw new RuntimeException("Unable to convert object to JSON.", e);
		}
	}

	/**
	 * Converts Java object to formatted JSON.
	 *
	 * @param object Java object
	 * @return formatted JSON
	 */
	public static String toPrettyJson(Object object) {

		try {

			return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);

		} catch (JsonProcessingException e) {

			throw new RuntimeException("Unable to convert object to formatted JSON.", e);
		}
	}

	/**
	 * Writes Java object to JSON file.
	 *
	 * @param object   Java object
	 * @param filePath output file
	 */
	public static void write(Object object, String filePath) {

		try {

			Path path = Path.of(filePath);

			Path parent = path.getParent();

			if (parent != null) {

				Files.createDirectories(parent);
			}

			OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), object);

		} catch (IOException e) {

			throw new RuntimeException("Unable to write JSON file: " + filePath, e);
		}
	}

	/**
	 * Converts JSON string to Java object.
	 *
	 * @param json  JSON string
	 * @param clazz target class
	 * @param <T>   target type
	 * @return Java object
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {

		try {

			return OBJECT_MAPPER.readValue(json, clazz);

		} catch (JsonProcessingException e) {

			throw new RuntimeException("Unable to convert JSON to object.", e);
		}
	}

	/**
	 * Converts JSON string to Map.
	 *
	 * @param json JSON string
	 * @return Map
	 */
	public static Map<String, Object> toMap(String json) {

		try {

			return OBJECT_MAPPER.readValue(json, Map.class);

		} catch (JsonProcessingException e) {

			throw new RuntimeException("Unable to convert JSON to Map.", e);
		}
	}
}