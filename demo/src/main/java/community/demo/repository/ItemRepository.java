package community.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import community.demo.domain.Item;
import community.demo.domain.QItem;
import community.demo.service.ItemService;
import community.demo.web.form.ItemUpdateForm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemRepository {

    private final EntityManager em;
    private JPAQueryFactory queryFactory;

    public ItemRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
//        return em.createQuery("select i from Item i", Item.class)
//                .getResultList();

        QItem i = new QItem("i");
        return queryFactory
                .selectFrom(i)
                .fetch();
    }
}
