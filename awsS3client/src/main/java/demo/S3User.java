package demo;

import awss3.S3client;
import com.amazonaws.regions.Regions;

import java.io.IOException;

public class S3User {

    // don't forget to set environment variables to it's values
    // it is very sensible data
    private static final String ACCESS_KEY = System.getenv("AWS_S3_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("AWS_S3_SECRET_KEY");
    private static final String BUCKET_NAME = System.getenv("AWS_S3_BUCKET_NAME");


    public static void main(String[] args) {
        if (ACCESS_KEY == null || SECRET_KEY == null || BUCKET_NAME == null) {
            throw new NullPointerException("Please set up environment variables to it's values!(see code)");
        }
        System.out.println("\n!Initializing client!\n");
        S3client client = new S3client(
                ACCESS_KEY,
                SECRET_KEY,
                Regions.US_EAST_2 // or whatever region you settled up
        );

        System.out.println("\n!Listing buckets!\n");
        client.listBuckets();

        System.out.println(String.format("\n!Listing files in selected bucket %s!\n", BUCKET_NAME));
        client.listFiles(BUCKET_NAME);

        System.out.println("\n!Uploading img.png as img.png!\n");
        client.upload(BUCKET_NAME, "img.png", "img.png");

        try {
            System.out.println("\n!Downloading img.png to imgDownloaded.png!\n");
            client.download(BUCKET_NAME, "img.png", "imgDownloaded.png");
        } catch (IOException e) {
            System.out.println("\n!Something went wrong!\n");
        }
        System.out.println("\n!Listing files!\n");
        client.listFiles(BUCKET_NAME);

        System.out.println("\n!Uploading img.png as img1.png!\n");
        client.upload(BUCKET_NAME, "img.png", "img1.png");
        System.out.println("\n!Listing files!\n");
        client.listFiles(BUCKET_NAME);

        System.out.println("\n!Uploading img.png as img2.png!\n");
        client.upload(BUCKET_NAME, "img.png", "img2.png");
        System.out.println("\n!Listing files!\n");
        client.listFiles(BUCKET_NAME);

        System.out.println("\n!Deleting single img2.png from server!\n");
        client.delete(BUCKET_NAME, "img2.png");
        System.out.println("\n!Listing files!\n");
        client.listFiles(BUCKET_NAME);

        System.out.println("\n!Deleting multiple files: img.png and img1.png!\n");
        client.multiDelete(BUCKET_NAME, new String[]{"img.png", "img1.png"});
        System.out.println("\n!Listing files!\n");
        client.listFiles(BUCKET_NAME);
    }
}
