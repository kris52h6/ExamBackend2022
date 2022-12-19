package dat.exam.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class SetupDevData implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        setupData();
    }

    private void setupData() {
    }
}
