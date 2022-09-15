package service;

import lombok.AllArgsConstructor;
import model.Sondage;
import org.springframework.stereotype.Service;
import repository.SondageRepository;

@Service
@AllArgsConstructor
public class SondageService {
    private final SondageRepository sondageRepository;

    public Sondage getSondage(String sondageId){
        return sondageRepository.getById(sondageId);
    }

}
