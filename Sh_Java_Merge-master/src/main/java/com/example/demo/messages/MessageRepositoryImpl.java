package com.example.demo.messages;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.demo.users.UserModel;
import com.example.demo.messages.Message;
import com.example.demo.messages.MessageSortModel;

public abstract class MessageRepositoryImpl implements MessageRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public List<Message> findByToUser(UserModel user, MessageSortModel messageSortModel) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> q = cb.createQuery(Message.class);
        Root<Message> root = q.from(Message.class);
        root.fetch("toUser");
        root.fetch("author");
        q.select(root).where(cb.equal(root.get("toUser"), user));
        if (StringUtils.equalsIgnoreCase(messageSortModel.getOrder(), "asc")) {
            q.orderBy(cb.asc(root.get(messageSortModel.getSort())));
        } else if (StringUtils.equalsIgnoreCase(messageSortModel.getOrder(), "desc")) {
            q.orderBy(cb.desc(root.get(messageSortModel.getSort())));
        }
        return em.createQuery(q).getResultList();
    }

}
