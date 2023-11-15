package in.rafiit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.rafiit.entity.Product;
import in.rafiit.repository.ProductRepo;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepo repo;

	@GetMapping("/")
	public String loadForm(Model model) {

		model.addAttribute("product", new Product());
		return "index";
	}

	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("product") Product p,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "index";
		}
		p=repo.save(p);
		if(p.getPid()!=null) {
			model.addAttribute("msg", "Product Saved");
		}
		return "index";
	}
	
	@GetMapping("/products")
	public String getAllProducts(Model model) {
		List<Product> list = repo.findAll();
		model.addAttribute("products", list);
		return "data";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("pid") Integer pid, Model model) {
		repo.deleteById(pid);
		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("products", repo.findAll());
		return "data";
	}

}
