package jpabook.jpashop.service.query;

import jpabook.jpashop.domain.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDto {

    // 필요한 데이터만 조회
    private String itemName; //상품 명
    private int orderPrice; //주문 가격
    private int count; //주문 수량

    //LAZY 초기화
    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}