package calculate;

import indexbuilder.Merge;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import configuration.EngineConfiguration;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CreatIndex ci = new CreatIndex();
		File[] indexFiles = ci.findFile(EngineConfiguration.subIndexDir);
		ArrayList<File> indexFilesA = new ArrayList<File>();
		for (int i = 0; i < indexFiles.length; ++i) {
			indexFilesA.add(indexFiles[i]);

		}
		String indexPath = Merge.merge(indexFilesA);
		System.out.println(indexPath);
		
//		File[] indexFiles = ci.findFile("./IndexOne");
//		ArrayList<File> indexFilesA = new ArrayList<File>();
//		for (int i = 0; i < indexFiles.length; ++i) {
//			System.out.println(indexFiles[i]);
//			indexFilesA.add(indexFiles[i]);
//
//		}
//		String dir = "./SourceDoc/";
//		File file = new File(dir);
//		
//		File[] files = file.listFiles();
//		for(File i : files)
//			System.out.println(i.toString());
//		
		

	}

}
