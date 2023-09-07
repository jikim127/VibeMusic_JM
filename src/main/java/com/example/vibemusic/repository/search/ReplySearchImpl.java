package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.QReply;
import com.example.vibemusic.domain.Reply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReplySearchImpl extends QuerydslRepositorySupport implements ReplySearch{

    public ReplySearchImpl() {super(Reply.class);}

    @Override
    public Page<Reply> searchAll(String[] types, String keyword, Pageable pageable) {
        QReply reply = QReply.reply;
        JPQLQuery<Reply> query = from(reply);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(reply.rreplyer.contains(keyword));
                        break;
                    case "a" :
                        booleanBuilder.or(reply.r_replyText.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(reply.rno.gt(0L));

        this.getQuerydsl().applyPagination(pageable,query);

        List<Reply> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}
