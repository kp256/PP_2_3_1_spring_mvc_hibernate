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

//    @GetMapping()
//    public String printCars(@RequestParam(value = "count", required = false) String count, ModelMap model) {
//        ArrayList<String> msg = new ArrayList<>();
//
//        msg.add("Cars page");
//        if (count != null) {
//            msg.add("You are requesting " + count + " car(s).");
//        }
//
//        model.addAttribute("msg", msg);
//
//        return "cars/cars";
//    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cars", carDAO.cars());
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
    public String createCar(@ModelAttribute("car") Car car, Model model) {
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