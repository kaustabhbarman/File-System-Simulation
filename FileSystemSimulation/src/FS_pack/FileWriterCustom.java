package FS_pack;

/**
 * Created by Rushil on 21-Apr-16.
 *
 */

import java.io.*;
import java.util.List;

public class FileWriterCustom {
    private static int DATA_WIDTH = 100, LOOKUP_TABLE_LENGTH = 100, META_DATA_LENGTH = 100, FILE_DATA_LENGTH = 800;
    String none_value;

    FileWriterCustom() {
        String none_value = "";
        for (int i = 0; i < DATA_WIDTH; i++) {
            none_value += '$';
        }
        this.none_value = none_value;
    }

    void file_data_write(List<StorageData> data) {
        String foo;
        for (StorageData node : data) {
            foo = conv_string(node.head) + '|' + conv_string_2(node.data) + '|' + conv_string(node.tail);
            write_data(foo, 2);
        }
    }

    StorageData parser_data(String data) {
        long head = 0, tail = 0;
        String dat = "";
        int marker = 0, prev_counter = 0;
        char ch;

        for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);

            if (ch == '|') {
                if (marker == 0) {
                    head = Long.parseLong(data.substring(0, i));
                    marker++;
                    prev_counter = i + 1;
                } else if (marker == 1) {
                    dat = data.substring(prev_counter, i).replaceAll("\\$", "");
                    tail = Long.parseLong(data.substring(i + 1, data.length()));
                }
            }
        }

        return new StorageData(dat, head, tail, 0);
    }

    StorageData parser_lut_file(String data) {
        long head = 0, tail = 0;
        String dat = "";
        int marker = 0, prev_counter = 0;
        char ch;

        for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);

            if (ch == '|') {
                if (marker == 0) {
                    head = Long.parseLong(data.substring(1, i));
                    marker++;
                    prev_counter = i + 1;
                } else if (marker == 1) {
                    dat = data.substring(prev_counter, i).replaceAll("\\$", "");
                    tail = Long.parseLong(data.substring(i + 1, data.length()));
                }
            }
        }

        return new StorageData(dat, head, tail, 0);
    }

    void write_data(String data, int marker) {
        // marker = 0 --> lookup table
        // marker = 1 --> meta data
        // marker = 2 --> normal data
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("container_temp.txt"));
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));

            String foo = br.readLine();
            int count = 0, flag = 0;

            if (marker == 0) {
                while (foo != null && count < LOOKUP_TABLE_LENGTH) {
                    if (foo.equals(this.none_value) && flag == 0) {
                        bw.write(data);
                        bw.newLine();
                        flag = 1;
                    } else {
                        bw.write(foo);
                        bw.newLine();
                    }
                    foo = br.readLine();
                    count++;
                }
            } else if (marker == 1) {
                while (foo != null && count < LOOKUP_TABLE_LENGTH) {
                    bw.write(foo);
                    bw.newLine();
                    foo = br.readLine();
                    count++;
                }
                count = 0;
                while (foo != null && count < META_DATA_LENGTH) {
                    if (foo.equals(this.none_value) && flag == 0) {
                        bw.write(data);
                        bw.newLine();
                        flag = 1;
                    } else {
                        bw.write(foo);
                        bw.newLine();
                    }
                    foo = br.readLine();
                    count++;
                }
            } else {
                while (foo != null && count < LOOKUP_TABLE_LENGTH + META_DATA_LENGTH) {
                    bw.write(foo);
                    bw.newLine();
                    foo = br.readLine();
                    count++;
                }
                count = 0;
                while (foo != null && count < FILE_DATA_LENGTH) {
                    if (foo.equals(this.none_value) && flag == 0) {
                        bw.write(data);
                        bw.newLine();
                        flag = 1;
                    } else {
                        bw.write(foo);
                        bw.newLine();
                    }
                    foo = br.readLine();
                    count++;
                }
            }
            while (foo != null) {
                bw.write(foo);
                bw.newLine();
                foo = br.readLine();
            }

            bw.close();
            br.close();

            File f = new File("container_temp.txt");
            File f1 = new File("container.txt");
            System.out.println(f1.delete());
            System.out.println(f.renameTo(f1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String conv_string(long a) {
        String bb = Long.toString(a);
        while (bb.length() < 7) {
            bb = '0' + bb;
        }
        return bb;
    }

    private String conv_string_2(String bb) {
        while (bb.length() < 84) {
            bb = '$' + bb;
        }
        return bb;
    }

}
