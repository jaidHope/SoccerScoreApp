import com.gradle.soccer.ScoreProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SoccerAppTest
{

	/**
	 * This test checks that a new file object is created based on the file path passed in.
	 */
	@Test
	public void createFileObjectFromFilenameTest()
	{
		String sep = File.separator;
		String file_expected = new File("src" + sep + "main" + sep + "resources" + sep + "input.txt").getAbsolutePath();

		File file_actual = ScoreProcessor.createFileObjectFromFilename("src\\main\\resources\\input.txt");

		assertEquals(file_expected.toString(), file_actual.toString());
	}

	

}
