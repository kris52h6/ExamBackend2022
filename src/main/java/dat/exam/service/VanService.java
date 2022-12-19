package dat.exam.service;

import dat.exam.dto.VanDTO;
import dat.exam.entity.Van;
import dat.exam.repository.VanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VanService {

    VanRepository vanRepository;

    public VanService(VanRepository vanRepository) {
        this.vanRepository = vanRepository;
    }

    public VanDTO addVan(VanDTO vanDTO) {
        Van newVan = VanDTO.getVanEntity(vanDTO);
        vanRepository.save(newVan);
        return new VanDTO(newVan);
    }

    public List<VanDTO> getAllVans() {
        return vanRepository.findAll().stream().map(v -> new VanDTO(v)).toList();
    }
}
