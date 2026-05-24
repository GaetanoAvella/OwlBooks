/*
INSERT INTO purchase_order (user_id,order_code,order_date,total,payment_method)
VALUES (1,'ORD-1779643137476389','2026-05-24',49.70,'credit_card');

INSERT INTO detail_order (order_id,book_id,quantity,price_at_purchase)
VALUES (1,18,1,13,90);

INSERT INTO detail_order (order_id,book_id,quantity,price_at_purchase)
VALUES (1,2,1,16,90);

INSERT INTO detail_order (order_id,book_id,quantity,price_at_purchase)
VALUES (1,10,1,18,90);

*/

SELECT * FROM purchase_order;