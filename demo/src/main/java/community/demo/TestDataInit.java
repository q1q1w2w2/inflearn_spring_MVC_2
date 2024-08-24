package community.demo;

import community.demo.domain.Item;
import community.demo.repository.ItemRepository;
import community.demo.service.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;

    @PostConstruct
    public void init() {
        itemService.save(new Item("itemA", 10000, 100));
        itemService.save(new Item("itemB", 20000, 200));
    }
}
