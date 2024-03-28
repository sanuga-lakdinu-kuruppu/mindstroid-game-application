import 'package:flutter/material.dart';

class TicTacToeWidget extends StatefulWidget {
  final String selectedOption;
  const TicTacToeWidget({required this.selectedOption});

  @override
  State<TicTacToeWidget> createState() => _TicTacToeWidgetState();
}

class _TicTacToeWidgetState extends State<TicTacToeWidget> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  late List<List<String>> _board;

  @override
  void initState() {
    super.initState();
    _initializeBoard();
  }

  void _initializeBoard() {
    _board = List.generate(3, (_) => List.filled(3, ''));
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Container(
        child: Form(
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              text(),
              ticTacToe(),
            ],
          ),
        ),
      ),
    );
  }

  Widget text() {
    return Container(
      child: Text(
        "Selected Option: ${widget.selectedOption}",
        style: TextStyle(
          color: Colors.white,
          fontSize: 32,
          fontWeight: FontWeight.bold,
          fontFamily: 'Inspiration',
        ),
      ),
    );
  }

  Widget ticTacToe() {
    final double gridViewWidth = 400.0;
    final double gridViewHeight = 400.0;

    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: Container(
        width: gridViewWidth,
        height: gridViewHeight,
        decoration: BoxDecoration(
          color: Color(0xFF5F6B84),
          borderRadius: BorderRadius.circular(10),
        ),
        margin: EdgeInsets.all(10),
        child: GridView.builder(
          itemCount: 9,
          shrinkWrap: true,
          physics: NeverScrollableScrollPhysics(),
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 3,
            childAspectRatio: 1.0,
            mainAxisSpacing: 0.0,
            crossAxisSpacing: 0.0,
          ),
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () {
                int row = index ~/ 3;
                int col = index % 3;
                _makeMove(row, col);
              },
              child: Container(
                decoration: BoxDecoration(
                  border: Border.all(
                    color: Color(0xFF0E1E3A),
                  ),
                ),
                child: Center(
                  child: Text(
                    _board[index ~/ 3][index % 3],
                    style: TextStyle(
                      color: _board[index ~/ 3][index % 3] == "X" ? Colors.red : Colors.green,
                      fontSize: 32,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            );
          },
        ),
      ),
    );
  }



  void _makeMove(int row, int col) {
    setState(() {
      if (_board[row][col].isEmpty) {
        _board[row][col] = widget.selectedOption;
      }
    });
  }
}
