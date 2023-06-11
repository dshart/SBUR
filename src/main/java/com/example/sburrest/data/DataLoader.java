package com.example.sburrest.data;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.sburrest.model.Coffee;
import com.example.sburrest.repository.CoffeeRepository;

@Component
public class DataLoader {
	private final CoffeeRepository coffeeRepository;

	public DataLoader(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
	}

	@PostConstruct
	private void loadData() {
		coffeeRepository.saveAll(List.of(
				new Coffee("Café Cereza"),
				new Coffee("Café Ganador"),
				new Coffee("Café Lareño"),
				new Coffee("Café Três Pontas")
		));
	}
}

