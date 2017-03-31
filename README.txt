FSA Interpreter

To run, compile all source code and run with an fsa file as an arg. Your command line should look like this:

$java fsaInterpreter ./fsa1.fsa

the fsa file should contain fsa rules and may look like such:

1 1 2 *
1 2 2
2 + 3
2 - 3
3 1 2
2 2 2
3 ( 4
4 1 5
4 2 5
5 + 6
5 - 6
5 ) 2
6 1 5
6 2 5

rules should come in rows of 3 (or 4 chars if final state). My program will return 'Not Accepted' if fsa doesn't reach final state and 'Accepted' if it does.
