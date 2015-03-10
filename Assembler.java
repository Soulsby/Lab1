import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads in an assembly file and converts it into machine-readable language.
 * 
 * 
 * IMM = address - PC +2
 * 
 * 
 * 
 * @author milnerml. Created Oct 26, 2014.
 */
public class Assembler {
	
	
	/**
	 * input file
	 */

	static File assemfile = new File("C:\\EclipseWorkspaces\\csse120\\AssemblerCSSE232\\src\\testfile.txt");
	/**
	 * output file (might not be used)
	 */
	static File outputfile;

	/**
	 * Main method of the assembler.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException {

		//assemfile = new File("testfile.txt");

		// if (!assemfile.canRead())
		// {
		// System.out.println("Error: cannot read from assembler file!");
		// return;
		// }

		// try {
		Scanner fileinput = new Scanner(assemfile);

		LookupTable table = new LookupTable();

		while (fileinput.hasNext()) {

			String line = fileinput.nextLine();
			Scanner lin = new Scanner(line);

			// get all labels
			switch (lin.next()) {

			case "noop":
				break;
				
			case "bin":
				break;

			// R-types
			case "addr":
				break;

			case "subr":
				break;

			case "sltr":
				break;

			case "and":
				break;

			case "or":
				break;

			case "xor":
				break;

			// I type 1
			case "addi":
				break;

			case "srai":
				break;

			case "srli":
				break;

			case "slli":
				break;

			case "andi":
				break;

			case "ori":
				break;

			case "bne":
				break;

			case "beq":
				break;

			// I type 2
			case "lwr":
				break;

			case "swr":
				break;

			// S type
			case "orl":
				break;

			case "lui":
				break;

			case "outl":
				break;

			case "outd":
				break;

			case "jr":
				break;

			case "inr":
				break;

			// J type
			case "j":
				break;

			case "jl":
				break;

			case "eret":
				break;

			case "break":
				break;

			default:
				table.addLabel(line, table.programcount);
				//System.out.println("Added "+line+" to table.");
				break;
			}
			table.programcount += 2;
		}
		
		fileinput = new Scanner(assemfile);
		table.programcount = 1026;

		while (fileinput.hasNext()) {
			// extract instruction line
			String instruction = fileinput.nextLine();

			// parse instruction

			String output = parseInstruction(instruction, table);
			System.out.println(output);

		}
		// } catch (Exception e) {
		//System.out.println("File could not be found.");
		return;
		// }

	}

	/**
	 * Parses the instruction into a binary number.
	 * 
	 * @param table
	 * 
	 * @param instr
	 * @return String
	 */
	private static String parseInstruction(String instruction, LookupTable table) {
		Scanner instructionScanner = new Scanner(instruction);

		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			switch (operation) {

			// because noop
			case "noop":
				return "0000000000000000";
				
			case "bin":
				return "1011000000011111";

				// R-types
			case "addr":
				return parseRType(instruction, table);

			case "subr":
				return parseRType(instruction, table);

			case "sltr":
				return parseRType(instruction, table);

			case "and":
				return parseRType(instruction, table);

			case "or":
				return parseRType(instruction, table);

			case "xor":
				return parseRType(instruction, table);

			// I type 1
			case "addi":
				return parseITypeOne(instruction, table);

			case "srai":
				return parseITypeOne(instruction, table);

			case "srli":
				return parseITypeOne(instruction, table);

			case "slli":
				return parseITypeOne(instruction, table);

			case "andi":
				return parseITypeOne(instruction, table);

			case "ori":
				return parseITypeOne(instruction, table);

			case "bne":
				return parseITypeOne(instruction, table);

			case "beq":
				return parseITypeOne(instruction, table);

			// I type 2
			case "lwr":
				return parseITypeTwo(instruction, table);

			case "swr":
				return parseITypeTwo(instruction, table);

			// S type
			case "orl":
				return parseSType(instruction, table);

			case "lui":
				return parseSType(instruction, table);

			case "outl":
				return parseSType(instruction, table);

			case "outd":
				return parseSType(instruction, table);

			case "jr":
				return parseSType(instruction, table);

			case "inr":
				return parseSType(instruction, table);

			// J type
			case "j":
				return parseJType(instruction, table);

			case "jl":
				return parseJType(instruction, table);

			case "eret":
				return parseJType(instruction, table);

			case "break":
				return parseJType(instruction, table);
			}

			table.programcount += 2;
		}

		return instruction;
	}

	/**
	 * Parses a given R-Type instruction.
	 * 
	 * @param s
	 * @param table
	 * @return String
	 */
	public static String parseRType(String s, LookupTable table) {
		Scanner instructionScanner = new Scanner(s);

		String output = "";

		// op code
		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			String opcode = table.getOP(operation);

			output += opcode;

		}

		// registers
		String r1 = "";
		String rd = "";
		String r2 = "";

		for (int i = 0; i < 3; i++) {
			if (!instructionScanner.hasNext()) {
				break;
			}

			else if (i == 0) {
				rd = table.getRegCode(instructionScanner.next());
			}

			else if (i == 1) {
				r1 = table.getRegCode(instructionScanner.next());
			}

			else if (i == 2) {
				r2 = table.getRegCode(instructionScanner.next());
			}

		}

		// put it together

		output += r1;
		output += rd;
		output += r2;

		return output + "00";
	}

	/**
	 * Parses a given I-Type instruction that uses arithmetic.
	 * 
	 * @param s
	 * @param table
	 * @return String
	 */
	public static String parseITypeOne(String s, LookupTable table) {
		Scanner instructionScanner = new Scanner(s);

		String output = "";

		// op code
		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			String opcode = table.getOP(operation);

			output += opcode;
		}

		// registers
		String rd = "";
		String r1 = "";
		if (instructionScanner.hasNext()) {
			rd = table.getRegCode(instructionScanner.next());
		}

		if (instructionScanner.hasNext()) {
			r1 = table.getRegCode(instructionScanner.next());
			output += (r1 + rd);
		}

		if (instructionScanner.hasNextInt()) {
			String imm = table.parseImmediate(instructionScanner.nextInt(), 5);
			output += imm;
		}

		else if (instructionScanner.hasNext()) {
			String label = instructionScanner.next();
			int address = table.getAddress(label);
			address = (address - (table.programcount +2))/2;
			String addressimm = table.parseImmediate(address, 5);
			output += addressimm;
		}

		else {
			output += "00000";
		}

		return output;
	}

	/**
	 * Parses a given I-type instruction that uses memory.
	 * 
	 * @param s
	 *            , table
	 * @param table
	 * @return String
	 */
	public static String parseITypeTwo(String s, LookupTable table) {
		Scanner instructionScanner = new Scanner(s);

		String output = "";

		// op code
		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			String opcode = table.getOP(operation);

			output += opcode;
		}

		String rd = "";
		String r1 = "";
		// registers and immediate
		if (instructionScanner.hasNext()) {
			rd = table.getRegCode(instructionScanner.next());
		}

		if (instructionScanner.hasNext()) {
			String part2 = instructionScanner.next();
			// System.out.println(part2);

			// extract immediate
			int sub1 = part2.indexOf("(");
			int imm = Integer.parseInt(part2.substring(0, sub1));
			// extract register
			r1 = table.getRegCode(part2.substring(sub1 +1, sub1 + 3));
			// parse immediate
			String immed = table.parseImmediate(imm, 5);
			// put together
			output = output + r1 + rd + immed;

		}

		return output;
	}

	/**
	 * Parses a given J-Type instruction.
	 * 
	 * @param s
	 * @param table
	 * @return String
	 */
	public static String parseJType(String s, LookupTable table) {
		Scanner instructionScanner = new Scanner(s);

		String output = "";

		// op code
		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			String opcode = table.getOP(operation);

			output += opcode;
		}

		if (instructionScanner.hasNextInt()) {
			String imm = table.parseImmediate(instructionScanner.nextInt(), 11);
			output += imm;
		}

		else if (instructionScanner.hasNext()) {
			String label = instructionScanner.next();
			int address = table.getAddress(label);
			String addressimm = table.parseImmediate(address, 11);
			output += addressimm;
		}

		else {
			// weird stuff
			output += "XXXXXXXXXXX";
		}

		return output;
	}

	/**
	 * Parses a given S-Type instruction.
	 * 
	 * @param s
	 * @param table
	 * @return String
	 */
	public static String parseSType(String s, LookupTable table) {
		Scanner instructionScanner = new Scanner(s);

		String output = "";

		// op code
		if (instructionScanner.hasNext()) {
			String operation = instructionScanner.next();

			String opcode = table.getOP(operation);

			output += opcode;

		}
		if (instructionScanner.hasNext()) {
			String reg = instructionScanner.next();

			String regCode = table.getRegCode(reg);

			output += regCode;
		}
		if (instructionScanner.hasNext()) {
			String imm = table.parseImmediate(instructionScanner.nextInt(), 8);
			output += imm;
		}
		
		else {
			output+="00000000";
		}
		
		return output;
	}
}
