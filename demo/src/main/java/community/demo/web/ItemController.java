package community.demo.web;

import community.demo.domain.Item;
import community.demo.web.form.ItemDto;
import community.demo.repository.ItemRepository;
import community.demo.service.ItemService;
import community.demo.web.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping
    public String itemMain() {
        return "item/itemMain";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("itemDto", new ItemDto());
        return "item/add";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemDto itemDto) {
        Item item = new Item(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
        itemService.save(item);
        return "redirect:/item/list";
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
    public String editItem(@PathVariable Long itemId, @ModelAttribute ItemUpdateForm form) {
        log.info("*** edit post 요청 ***");
        log.info("itemUpdateForm: {}", form);
        itemService.updateItem(itemId, form);
        return "redirect:/item/list";
    }
}
