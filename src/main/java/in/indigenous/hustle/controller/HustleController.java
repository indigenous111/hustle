package in.indigenous.hustle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.indigenous.hustle.models.DwellerSummary;
import in.indigenous.hustle.services.DownloadSummaryService;
import in.indigenous.hustle.utils.StringUtils;

@Controller
public class HustleController {

	@Autowired
	private Environment env;

	@Autowired
	private StringUtils stringUtils;
	
	@Autowired
	private Map<String, List<String>> compMap;
	
	@Autowired
	private DownloadSummaryService downloadSummaryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handle(@RequestParam(required = false, defaultValue = "home") String page, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/dwellers/summary", method = RequestMethod.GET)
	@ResponseBody
	public List<List<String>> getDwellerSummary() {
		return downloadSummaryService.getDwellerSummary();
	}

	private String getPageTitle(String page) {
		String key = stringUtils.getConcatenetedString(".", page, "title");
		return env.getProperty(key);
	}

	private String getAppName() {
		return env.getProperty("application.name");
	}

	private String getAppVersion() {
		StringBuilder builder = new StringBuilder(getAppName());
		builder.append("-v");
		builder.append(stringUtils.getConcatenetedString(".", env.getProperty("application.version"),
				env.getProperty("application.major.version"), env.getProperty("application.minor.version")));
		return builder.toString();
	}
	
	private String getAppSlogan() {
		return env.getProperty("application.slogan");
	}

	private String getPagePath(String page) {
		StringBuilder builder = new StringBuilder();
		builder.append("../");
		for(String key: compMap.keySet()) {
			if(compMap.get(key).contains(page)) {
				builder.append(key);
				builder.append("/");
				break;
			}
		}
		builder.append(page);
		builder.append(".jsp");
		return builder.toString();
	}
}
