package community.demo.web;

import community.demo.domain.Item;
import community.demo.web.form.ItemAddForm;
import community.demo.service.ItemService;
import community.demo.web.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String itemMain() {
        return "item/itemMain";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("itemAddForm", new ItemAddForm());
        return "item/add";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute ItemAddForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult= {}", bindingResult);
            return "/item/add";
        }

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
        itemService.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/item/detail/{itemId}";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "item/list";
    }

    @GetMapping("/detail/{itemId}")
    public String detail(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.findById(itemId));
        return "item/detail";
    }

    @GetMapping("/edit/{itemId}")
    public String edit(@PathVariable Long itemId, Model model) {
        log.info("*** edit get 요청 ***");
        model.addAttribute("item", itemService.findById(itemId));
        return "item/edit";
    }

    @PostMapping("/edit/{itemId}")
    public String editItem(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {
        log.info("*** edit post 요청 ***");
        log.info("itemUpdateForm: {}", form);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult= {}", bindingResult);
            return "item/edit";
        }

        itemService.updateItem(itemId, form);

        return "redirect:/item/detail/{itemId}";
    }

}
