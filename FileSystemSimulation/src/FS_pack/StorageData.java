package FS_pack;

/**
 * Created by Rushil on 22-Apr-16.
 */

class StorageData {
    long head = 0, tail = 0, curr = 0;
    String data = "";

    StorageData(String data, long head, long tail, long curr) {
        this.data = data;
        this.head = head;
        this.tail = tail;
        this.curr = curr;
    }

}