package in.indigenous.hustle.utils;

import java.util.StringJoiner;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

	public final String getConcatenetedString(String separator, String... params) {
		StringJoiner builder = new StringJoiner(separator);
		if (isNotEmpty(params)) {
			for (String param : params) {
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(param)) {
					builder.add(param);
				}
			}
		}
		return builder.toString();
	}

	public final <T> boolean isNotEmpty(T[] arg) {
		return !(arg == null && arg.length == 0);
	}
}
