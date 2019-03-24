package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {
		
		private static String filename = "data.block223";
		
		public static void save(Block223 block223) {
			PersistenceObjectStream.serialize(block223);
		}
		
		public static Block223 load() {
			PersistenceObjectStream.setFilename(filename);
			Block223 block223 = (Block223) PersistenceObjectStream.deserialize();
			
			// model cannot be loaded - create empty Block223
			if (block223 == null) {
				block223 = new Block223();
			}
			
			else {
				//Once reinitialize is fixed we need to uncomment it
				//and not have a new block223 be created
				block223.reinitialize();
				//block223 = new Block223();
			}
			
			return block223;
		}
		
		public static void setFilename(String newFilename) {
			filename = newFilename;
		}

	}

