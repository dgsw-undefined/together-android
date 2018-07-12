package dgsw.hs.kr.gatchigachi.database

/**
 * @author dawncrow
 */

internal class DatabaseManager {

    val createTableDepartment = "CREATE TABLE department ( " +
            "idx INTEGER UNIQUE, " +
            "name STRING, " +
            "tel STRING, " +
            "PRIMARY KEY('idx') " +
            ")"

    val createTableMember = (
            "CREATE TABLE member ( " +
                    "id STRING UNIQUE, " +
                    "name STRING, " +
                    "phone STRING, " +
                    "email STRING, " +
                    "profile_image STRING, " +
                    "profile_image_byte BLOB, " +
                    "status_message STRING, " +
                    "department_idx INTEGER, " +
                    "position STRING, " +
                    "task STRING, " +
                    "auth INTEGER, " +
                    "status INTEGER, " +
                    "is_me BOOL, " +
                    "PRIMARY KEY('id')," +
                    "FOREIGN KEY(department_idx) REFERENCES Department(idx) " +
                    ")")

    val createTableBoard = (
            "CREATE TABLE board ( " +
                    "idx INTEGER UNIQUE, " +
                    "title STRING, " +
                    "writer STRING, " +
                    "content STRING, " +
                    "type INTEGER, " +
                    "write_date DATETIME, " +
                    "modify_date DATETIME, " +
                    "reservation_at DATETIME, " +
                    "FOREIGN KEY(writer) REFERENCES member(id)," +
                    "PRIMARY KEY('idx') " +
                    ")")

    val createTableBoardReceiver = (
            "CREATE TABLE board_receiver ( " +
                    "idx INTEGER, " +
                    "receiver STRING, " +
                    "FOREIGN KEY(idx) REFERENCES board(idx)," +
                    "FOREIGN KEY(receiver) REFERENCES member(id)," +
                    "PRIMARY KEY('idx', 'receiver') " +
                    ")")

    val createTableComment = (
            "CREATE TABLE comment ( " +
                    "idx INTEGER UNIQUE, " +
                    "board_idx INTEGER, " +
                    "content STRING, " +
                    "writer STRING, " +
                    "created_at DATETIME, " +
                    "updated_at DATETIME, " +
                    "original_name STRING, " +
                    "upload_name STRING, " +
                    "type STRING, " +
                    "FOREIGN KEY(writer) REFERENCES member(id), " +
                    "FOREIGN KEY(board_idx) REFERENCES board(idx) on delete cascade, " +
                    "PRIMARY KEY('idx') " +
                    ")")

    val createTableToken = (
            "CREATE TABLE token ( " +
                    "idx INTEGER UNIQUE, " +
                    "token STRING, " +
                    "refresh_token STRING, " +
                    "PRIMARY KEY('idx')" +
                    ")")

    val createTableFavorite = (
            "CREATE TABLE favorite ( " +
                    "idx INTEGER UNIQUE, " +
                    "member_id STRING, " +
                    "FOREIGN KEY(member_id) REFERENCES member(id), " +
                    "PRIMARY KEY('idx')" +
                    ")")

    val createTableChatting = (
            "CREATE TABLE chatting ( " +
                    "idx INTEGER UNIQUE, " +
                    "room_idx INTEGER, " +
                    "message STRING, " +
                    "member_id STRING, " +
                    "type STRING, " +
                    "original_name STRING, " +
                    "upload_name STRING, " +
                    "create_date STRING, " +
                    "FOREIGN KEY(member_id) REFERENCES member(id), " +
                    "FOREIGN KEY(room_idx) REFERENCES chatting_room(chat_room_idx), " +
                    "PRIMARY KEY('idx')" +
                    ")")

    val createTableChattingRoom = (
            "CREATE TABLE chatting_room ( " +
                    "idx INTEGER UNIQUE, " +
                    "type STRING, " +
                    "name STRING, " +
                    "member_id STRING, " +
                    "FOREIGN KEY(member_id) REFERENCES member(id), " +
                    "PRIMARY KEY('idx')" +
                    ")")

    val createTableChattingRoomMember = (
            "CREATE TABLE chatting_room_member ( " +
                    "room_idx INTEGER," +
                    "member_idx INTEGER," +
                    "PRIMARY KEY('room_idx', 'member_idx')" +
                    ")")
}
