package community.demo;

import community.demo.domain.Item;
import community.demo.domain.Member;
import community.demo.service.ItemService;
import community.demo.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final MemberService memberService;

    @PostConstruct
    public void init() {
        itemService.save(new Item("itemA", 10000, 100));
        itemService.save(new Item("itemB", 20000, 200));
        memberService.save(new Member("memberA", "test", "test", "test@gmail.com"));
    }
}
