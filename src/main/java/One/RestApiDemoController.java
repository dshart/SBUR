package One;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
	private List<Coffee> coffees = new ArrayList<>();
	
	public RestApiDemoController() {
		coffees.addAll(List.of(
					new Coffee("Folgers"),
					new Coffee("Maxwell House"),
					new Coffee("Peet's"),
					new Coffee("Starbucks")
		));			
	}

	@GetMapping
	Iterable<Coffee> getCoffee() {
		return coffees;
	}
	
	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		for (Coffee c : coffees) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
	
	@PostMapping
	Coffee postCoffee(@RequestBody Coffee coffee) {
		coffees.add(coffee);
		return coffee;
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Object> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
		int coffeeIndex = -1;
		
		for (Coffee c : coffees) {
			if (c.getId().equals(id)) {
				coffeeIndex = coffees.indexOf(c);
				coffees.set(coffeeIndex, coffee);
			}
		}
		return (coffeeIndex == -1) ? 
			new ResponseEntity<Object>(postCoffee(coffee), HttpStatus.CREATED) :
			new ResponseEntity<Object>(coffee, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		//coffees.removeIf(c -> c.getId().equals(id));
		for (Coffee c : coffees) {
			if (c.getId().equals(id))
				coffees.remove(c);
		}
	}	
}	