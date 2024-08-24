package community.demo.service;

import community.demo.domain.Item;
import community.demo.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 200);

        // when
        Long saveId = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(saveId);
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 200);
        Item item2 = new Item("itemB", 20000, 300);
        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> all = itemService.findAll();

        // then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);

    }
}