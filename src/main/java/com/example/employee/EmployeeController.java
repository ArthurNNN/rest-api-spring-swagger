package com.example.employee;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping("/")
	public String getAllEmployees(Model boxToView) {

		boxToView.addAttribute("employeeListfromControllerAndDB", employeeRepository.findAll());

		return "home";
	}

	@RequestMapping("/fillIn10Employees")
	public String fillIn10Employees(Model boxToView) {

		addFakeEmployees(10);

		boxToView.addAttribute("employeeListfromControllerAndDB", employeeRepository.findAll());

		return "redirect:/";
	}

	// -----------------------read by Id----------------------------------
	@RequestMapping("/detailEmployee")
	public String detailEmpoyee(int id, Model model) {

		Optional<Employee> employeeFound = findOneEmployeeById(id);

		if (employeeFound.isPresent()) {

			model.addAttribute("employeefromController", employeeFound.get());
			return "detailemployee";
		}

		else
			return "notfound.html";
	}

	@RequestMapping("/updateEmployee")
	public String updateEmpoyee(int id, Model model) {

		Optional<Employee> employeeFound = findOneEmployeeById(id);

		if (employeeFound.isPresent()) {

			model.addAttribute("employeefromController", employeeFound.get());
			return "updateemployee";
		}

		else
			return "notfound.html";
	}

	// -----------------------update by Id----------------------------------
	@PostMapping("/replaceEmployee/{idFromView}")
	public String replaceEmployee(@PathVariable("idFromView") int id, Employee employee) {

		Optional<Employee> employeeFound = findOneEmployeeById(id);

		if (employeeFound.isPresent()) {

			if (employee.getName() != null)
				employeeFound.get().setName(employee.getName());
			if (employee.getSurname() != null)
				employeeFound.get().setSurname(employee.getSurname());
//				if (employee.getPassword() != null)
//					employeeFound.get().setPassword(employee.getPassword());
			if (employee.getEmail() != null)
				employeeFound.get().setEmail(employee.getEmail());
			if (employee.getAge() != 0)
				employeeFound.get().setAge(employee.getAge());
			if (employee.getBloodType() != null)
				employeeFound.get().setBloodType(employee.getBloodType());
			if (employee.getMonthSalary() != 0.0)
				employeeFound.get().setMonthSalary(employee.getMonthSalary());

			employeeRepository.save(employeeFound.get());
			return "redirect:/";

		} else
			return "notfound.html";

	}

	// -----------------------delete----------------------------------
	@RequestMapping("/deleteEmployee")
	public String removeEmployee(int id, Model model) {

		System.out.println("inside removeEmployee" + id);
		Optional<Employee> employeeFound = employeeRepository.findById(id);

		System.out.println("find inside removeEmployee" + employeeFound.get());

		if (employeeFound.isPresent()) {

			employeeRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("employeeDeleted", employeeFound.get());
		}

		else {
			model.addAttribute("message", "error");
		}

		System.out.println("finishing removeEmployee" + id);
		return "deletedemployee";
	}

	@RequestMapping("/deleteAllEmployees")
	public String deleteAllEmployees() {

		employeeRepository.deleteAll();

		return "redirect:/";

	}

	// --------------------------------------------------------------------------------
	// ------------------------- service to controller
	// --------------------------------
	// --------------------------------------------------------------------------------

	protected void addFakeEmployees(int qt) {
		Faker faker = new Faker();

		System.out.print("\n---------------- Add employees: ----------------");
		int n = 1;
		while (n <= qt) {
			Employee employee = new Employee();
			employee.setName(faker.name().firstName());
			employee.setSurname(faker.name().lastName());
			employee.setAge((int) ((Math.random() * (130 - 18)) + 18));

			String[] bloodTypeList = new String[] { "I", "II", "III", "IV" };
			Random rand = new Random();
			String randomBloodType = bloodTypeList[rand.nextInt(bloodTypeList.length)];
			employee.setBloodType(randomBloodType);

			employee.setEmail(faker.internet().emailAddress());
			employee.setMonthSalary((int) ((Math.random() * (10000 - 0)) + 0));

			employeeRepository.save(employee);
			System.out.print("\n#" + n + " ");
			System.out.print(employee);
			n++;

		}
	}

	public Optional<Employee> findOneEmployeeById(int id) {

		// System.out.println("inside findEmployee" + id);
		Optional<Employee> employeeFound = employeeRepository.findById(id);
		// System.out.println("finishing findEmployee" + id);
		// System.out.println("finishing findEmployee" + employeeFound.get());
		return employeeFound;
	}

}
