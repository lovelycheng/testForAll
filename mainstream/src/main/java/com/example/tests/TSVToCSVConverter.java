package com.example.tests;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author chengtong
 * @date 2020/10/28 13:29
 */
public class TSVToCSVConverter {
    public static void main(String[] args) throws Exception {

        //The input TSV File
        String tsvFilePath = "/Users/chengtong/downloads/imdbdata/name.basics.tsv";

        //The output CSV File
        String csvFilePath = "/Users/chengtong/downloads/imdbdata/name.basics.csv";

        convertTSVToCSVFile(csvFilePath, tsvFilePath);

    }

    /**
     * Converts a TSV file into CSV file.
     * - Reads one line at a time
     * - Replaces Double Quotes with Single Quotes
     * - Puts Double Quotes Around Every Field
     * - Fileds in the output are separated by comma.
     *
     * @param csvFilePath
     * @param tsvFilePath
     * @throws IOException
     */
    private static void convertTSVToCSVFile(String csvFilePath, String tsvFilePath) throws IOException {

        //TODO: If outfile does not exist, create one.

        StringTokenizer tokenizer;
        try (BufferedReader br = new BufferedReader(new FileReader(tsvFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath));) {

            int i = 0;
            for (String line; (line = br.readLine()) != null; ) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println("Processed: " + i);

                }
                tokenizer = new StringTokenizer(line, "\t");

                String csvLine = "";
                String token;
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken().replaceAll("\"", "'");
                    csvLine += "\"" + token + "\",";
                }

                if (csvLine.endsWith(",")) {
                    csvLine = csvLine.substring(0, csvLine.length() - 1);
                }

                writer.write(csvLine + System.getProperty("line.separator"));

            }

        }


    }

    private static String convertToCSV(String line) {
        String csv = "";
        line = line.replaceAll("\t", ",");
        return line;
    }
}
