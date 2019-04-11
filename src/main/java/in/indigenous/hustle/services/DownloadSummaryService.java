package in.indigenous.hustle.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.indigenous.hustle.models.DwellerSummary;
import in.indigenous.hustle.repository.DwellerRepository;

@Service
public class DownloadSummaryService {

	@Autowired
	private DwellerRepository dwellerRepository;

	public List<List<String>> getDwellerSummary() {
		List<DwellerSummary> summaries = new ArrayList<>();
		dwellerRepository.findAll().forEach(dweller -> {
			DwellerSummary summary = new DwellerSummary();
			summary.setId(dweller.getId());
			summary.setName(dweller.getName());
			summaries.add(summary);
		});
		List<List<String>> result = new ArrayList<>();
		summaries.forEach(sum -> {
			List<String> list = new ArrayList<>();
			list.add(String.valueOf(sum.getId()));
			list.add(sum.getName());
			result.add(list);
		});
		return result;
	}
}
