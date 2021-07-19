/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE m_user;
DROP TABLE task;
DROP TABLE testreport;

 */

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    encrypted_password VARCHAR(100),
    user_name VARCHAR(50),
    darkmode BOOLEAN,
    role VARCHAR(50),
    enabled BOOLEAN
);

/* タスクテーブル */
CREATE TABLE IF NOT EXISTS task (
  id INT PRIMARY KEY,
  user_id VARCHAR(50),
  priority VARCHAR(10),
  title VARCHAR(50),
  comment VARCHAR(200),
  limitday DATE
);

/* 受験報告テーブル */
CREATE TABLE IF NOT EXISTS testreport (
  id INT PRIMARY KEY,
  status VARCHAR(10),
  user_name VARCHAR(60),
  user_class CHAR(4),
  attendance_number INT(2),
  day DATE
);