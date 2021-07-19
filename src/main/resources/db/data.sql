/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM m_user;
DELETE FROM task;
DELETE FROM testreport;

/* ユーザマスタのデータ（ADMIN権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('isida@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '石田悠介', false, 'ROLE_ADMIN', true);
/* ユーザマスタのデータ（一般権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('abe@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '安部華奈', false, 'ROLE_GENERAL', true);
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('sano@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '佐野翼', true, 'ROLE_GENERAL', false);

/* タスクテーブルのデータ */
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (1, 'isida@xxx.co.jp', 'HIGH','a', 'これやる', '2020-03-23');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (2, 'abe@xxx.co.jp', 'MIDDLE','b', 'あれやる', '2020-03-24');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (3, 'isida@xxx.co.jp', 'LOW','c', 'それやる', '2020-03-31');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (4, 'sano@xxx.co.jp', 'LOW','d', 'どれやる', '2020-03-25');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (5, 'abe@xxx.co.jp', 'LOW','e', 'もっとやる', '2020-04-20');

/* 受験報告テーブルのデータ */
INSERT INTO testreport (id, status, user_name, user_class, attendance_number, day) VALUES (1, '差戻し', '石田悠介', 'S3A3', 01, '2020-03-23');
INSERT INTO testreport (id, status, user_name, user_class, attendance_number, day) VALUES (2, '承認待', '安部華奈', 'S3A1', 12, '2020-03-24');
INSERT INTO testreport (id, status, user_name, user_class, attendance_number, day) VALUES (3, '承認待', '石田悠介', 'S3A3', 01, '2020-03-31');
INSERT INTO testreport (id, status, user_name, user_class, attendance_number, day) VALUES (4, '承認待', '佐野翼', 'S3A2', 35, '2020-03-25');
INSERT INTO testreport (id, status, user_name, user_class, attendance_number, day) VALUES (5, '差戻し', '安部華奈', 'S3A1', 12, '2020-04-20');