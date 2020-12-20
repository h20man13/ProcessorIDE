# EDE - Emulator Debug Enviroment
Previously called Processor IDE
# What is it?
The EDE is software that is inspired by the PEP9 virtual computer. Pep 9 is an educational tool that allows students to learn the basics of how a computer works without having to dive into any actual hardware. It is a much more cost effective solution for universities to teach undergrad/introductory level assembly/computer systems courses. The clever GUI also allows the students to easily visualize whats going on inside the CPU. It does have its limits however. Since PEP 9 is not an actual computer the assembly syntax is useless in industry. I had some experience with arm assembly in the past so I thought initially why don't I make a similar project with an ARM virtual computer or at least a new assembler that would change the input syntax to something more similar to arm. Then I thought why don't I also make this tool into a tool that can be used to teach a computer architecture class. I could allow the user to use an Hardware description Language to create their emulator. The advantage of an HDL is that the instructor can go into various levels of specificity from the descriptive level to the structural level of the processor. Then the user could use any language they want to make an assembler. With those two things created they could "load" them into the environment and create some code to verify their processor works correctly.
# Software Used in this Poject
Java - main language <br>
Javafx - API to create the IDE <br>
Icarus Verilog - Verilog Simulator used to run the code <br>
GTKWAVE - Waveform Analysis tool

# Features Completed
Java - Tool to automatically create testbenches and open GTKWave with the newly created test file

# Important Info
Currently put on pause because I am moving this to become my senior project starting in Febuary