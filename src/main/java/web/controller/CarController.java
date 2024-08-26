package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Car;
import web.service.CarServiceImpl;


@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars/cars";
    }

    @GetMapping(params = "count")
    public String index(@RequestParam(value = "count", required = false) Integer count, Model model) {
        model.addAttribute("cars", carService.getCarsByCount(count));
        model.addAttribute("count", count);
        return "cars/cars";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/show";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    @PostMapping()
    public String createCar(@ModelAttribute("car") Car car) {
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/edit";
    }

    @PatchMapping("/{id}")
    public String updateCar(@ModelAttribute("car") Car car, @PathVariable("id") int id) {
        carService.updateCar(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return "redirect:/cars";
    }

}