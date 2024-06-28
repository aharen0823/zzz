package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Csv {
	//うんこうんこうんこ
	try {
	private static final String FILE_NAME = "C:\\Users\\ito11\\デスクトップ\\git_clone_port\\zzz\\test_utf8.csv";
	}catch(ClassNotFoundException e) {
		System.out.println("error");
	}
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("1.リスト  と入力するとリストの中身を確認できます");
		System.out.println("2.翻訳したいなら：翻訳  と入力してください");

		String input = scanner.nextLine();

		if ("リスト".equals(input)) {
			translationList();

		} else if ("翻訳".equals(input)) {
			System.out.println("日本語の文字を入力してください：");

			String japaneseInput = scanner.nextLine();
			//メソッドなので動きにする名前（toなどつけて記述する）
			toJapanese(japaneseInput);
		} else {
			System.out.println("入力に誤りがあります。プログラムを終了します。");
		}
		scanner.close();

	}

	public static void translationList() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME, StandardCharsets.UTF_8));

			String line;

			//1行目飛ばすため
			boolean headerSkipped = false;
			while ((line = reader.readLine()) != null) {
				if (!headerSkipped) {
					headerSkipped = true;
					continue;
				}
				String[] keyward = line.split(",");
				if (keyward.length >= 2) {
					String englishWord = keyward[0].trim();
					String japaneseWord = keyward[1].trim();
					printTranslation(englishWord, japaneseWord);
				}
			}
			reader.close();

		} catch (IOException e) {
			System.out.println("ファイル " + FILE_NAME + " が見つかりません。");
		}
	}
	public static void printTranslation(String english, String japanese) {

		System.out.println("-----------------------");
		System.out.printf("%-10s | %-10s%n", english, japanese);
	}

	public static void toJapanese(String japaneseInput) {
		List<String> englishWords = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME, StandardCharsets.UTF_8));
			String str;
			//日本語の時同様に一行目飛ばしたいため
			boolean headerSkipped = false;
			
			while ((str = reader.readLine()) != null) {
				if (!headerSkipped) {
					headerSkipped = true;
					continue;
				}

				String[] words = str.split(",");
				if (words.length >= 2) {
					String englishWord = words[0].trim();
					String japaneseWord = words[1].trim();
					if (japaneseWord.equals(japaneseInput)) {
						englishWords.add(englishWord);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("ファイル " + FILE_NAME + " が見つかりません。");
		}
		if (englishWords.isEmpty()) {

			System.out.println("指定された日本語の単語は翻訳リストにありません。");
		} else {
			System.out.println("翻訳結果:");
			for (String englishWord : englishWords) {
				System.out.println(englishWord);
			}
		}
	}
}
