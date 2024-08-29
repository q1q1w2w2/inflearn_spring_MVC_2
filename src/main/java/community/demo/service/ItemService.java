package community.demo.service;

import community.demo.domain.Item;
import community.demo.repository.ItemRepository;
import community.demo.web.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateForm form) {
        Item item = itemRepository.findById(itemId);
        item.updateItem(form);
    }
}
