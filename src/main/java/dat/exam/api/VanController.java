package dat.exam.api;

import dat.exam.dto.VanDTO;
import dat.exam.service.VanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/vans")
public class VanController {

    VanService vanService;

    public VanController(VanService vanService) {
        this.vanService = vanService;
    }

    @PostMapping
    public VanDTO addVan(@RequestBody VanDTO vanDTO) {
        return vanService.addVan(vanDTO);
    }

    @GetMapping
    public List<VanDTO> getAllVans() {
        return vanService.getAllVans();
    }
}
