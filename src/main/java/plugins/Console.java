package plugins;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.util.Date;

import imgui.ImGui;
import imgui.type.ImString;
import nixstudio.Feather.Core.Gui;
import nixstudio.Feather.Core.GuiMain;
import nixstudio.Feather.Core.StudioWindow;

public class Console extends StudioWindow{

	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	PrintStream old = System.out;
	PrintStream ps = new PrintStream(stream);
	ImString text = new ImString();

	public Console(GuiMain main) {
		super(main);
		System.setOut(ps);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Console";
	}

	@Override
	public void guiUpdate() {
		// TODO Auto-generated method stub
		if(Gui.button("Clear")){
			stream.reset();
		}
		Gui.inputText("##", text);
		Gui.sameLine();
		if(Gui.button("Enter")) {
			enterCommand(text.get());
			text.set("");
		}
		
		Gui.separator();
		Gui.textWrapped(stream.toString());
	} 

	void enterCommand(String command) {
		String[] args = command.split(" ");
		try {
			Process proc = new ProcessBuilder(args).start();
			OutputStream stream = proc.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			String line = "";
			while((line = reader.readLine()) != null) {
				System.out.print(line + "\n");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace(ps);
		}
	}
}
