package FS_pack;

/**
 * Created by Rushil on 20-Apr-16.
 **/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class FileCreate {
    private static int DATA_WIDTH = 100, LOOKUP_TABLE_LENGTH = 100, META_DATA_LENGTH = 100, FILE_DATA_LENGTH = 800;
    private String file_data, none_value;

    //    Constructor
    FileCreate(String file_data) {
        this.file_data = file_data;
        String none_value = "";
        for (int i = 0; i < DATA_WIDTH; i++) {
            none_value += '$';
        }
        this.none_value = none_value;
    }

    long store_file() {
//        Divide given data in chunks
        String[] chunks = new String[100];
        int n = 0;
        char a;
        long[] ch = new long[100];
        List<StorageData> DATA = new ArrayList<>();
        long bar = 0;
        chunks[0] = "";
        for (int bytes = 0; bytes < this.file_data.length(); bytes++) {

            if (chunks[n].length() >= 84) {

                n++;
                chunks[n] = "";
            }
            chunks[n] = chunks[n] + this.file_data.charAt(bytes);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
            String foo = br.readLine();
            int count = 0;
            while (foo != null && count < LOOKUP_TABLE_LENGTH + META_DATA_LENGTH) {
                foo = br.readLine();
                count++;
            }
            count = 0;
            int i = 0;
            while (foo != null && count < FILE_DATA_LENGTH && i <= n) {
                if (foo.equals(this.none_value)) {
                    ch[i] = count + LOOKUP_TABLE_LENGTH + META_DATA_LENGTH;
                    i++;
                }
                foo = br.readLine();
                count++;
            }
            i = 0;
            StorageData sd;
            for (i = 0; i <= n; i++) {
                if (i == 0) {
                    sd = new StorageData(chunks[i], 0, ch[i + 1], ch[i]);
                } else if (i == n) {
                    sd = new StorageData(chunks[i], ch[i - 1], 0, ch[i]);
                } else {
                    sd = new StorageData(chunks[i], ch[i - 1], ch[i + 1], ch[i]);
                }
                DATA.add(sd);

            }
            br.close();

            FileWriterCustom obj = new FileWriterCustom();
            obj.file_data_write(DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ch[0];
    }
}
