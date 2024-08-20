package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.CarDAO;
import web.model.Car;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarDAO carDAO;

    @Autowired
    public CarController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @GetMapping()
    public String index(@RequestParam(value = "count", required = false, defaultValue = "5") Integer count, Model model) {
        model.addAttribute("cars", carDAO.cars(count));
        model.addAttribute("count", count);
        return "cars/cars";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carDAO.get(id));
        return "cars/show";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    @PostMapping()
    public String createCar(@ModelAttribute("car") Car car) {
        carDAO.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carDAO.get(id));
        return "cars/edit";
    }

    @PatchMapping("/{id}")
    public String updateCar(@ModelAttribute("car") Car car, @PathVariable("id") int id) {
        carDAO.update(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carDAO.delete(id);
        return "redirect:/cars";
    }

}