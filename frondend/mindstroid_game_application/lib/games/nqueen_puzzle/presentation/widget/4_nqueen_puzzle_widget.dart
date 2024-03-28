import 'package:flutter/material.dart';

class NQueenPuzzleWidget extends StatefulWidget {
  const NQueenPuzzleWidget({super.key});

  @override
  State<NQueenPuzzleWidget> createState() => _NQueenPuzzleWidgetState();
}

class _NQueenPuzzleWidgetState extends State<NQueenPuzzleWidget> {

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  late List<List<String>> _board;

  @override
  void initState() {
    super.initState();
    _initializeBoard();
  }

  void _initializeBoard() {
    _board = List.generate(8, (_) => List.filled(8, ''));
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
              ticTacToe(),
            ],
          ),
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
          itemCount: 64,
          shrinkWrap: true,
          physics: NeverScrollableScrollPhysics(),
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 8,
            childAspectRatio: 1.0,
            mainAxisSpacing: 0.0,
            crossAxisSpacing: 0.0,
          ),
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () {
                print([index ~/ 8, index % 8] );  // [0, 0]])

              },
              child: Container(
                decoration: BoxDecoration(
                  border: Border.all(
                    color: Color(0xFF0E1E3A),
                  ),
                ),
              ),
            );
          },
        ),
      ),
    );
  }

}
