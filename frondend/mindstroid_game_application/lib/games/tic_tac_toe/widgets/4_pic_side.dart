import 'package:flutter/material.dart';
import 'package:mindstroid_game_application/constants/image_strings.dart';
import 'package:mindstroid_game_application/games/tic_tac_toe/widgets/5_tic_tac_toe_widget.dart';

class PicSideWidget extends StatelessWidget {
  const PicSideWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            text(),
            SizedBox(
              height: 40,
            ),
            buttons(context),
          ],
        ),
      ),
    );
  }

  Widget text() {
    return Text(
      "Pic Your Side?",
      style: TextStyle(
        color: Colors.white,
        fontSize: 32,
        fontWeight: FontWeight.bold,
        fontFamily: 'Inspiration',
      ),
    );
  }

  Widget buttons(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          ElevatedButton(
            onPressed: () {
              _showDialog(context, 'X');
            },
            child: const Text(
              "X",
              style: TextStyle(
                fontSize: 40,
                fontWeight: FontWeight.bold,
                color: Colors.black,
                fontFamily: 'Inspiration',
              ),
            ),
            style: ElevatedButton.styleFrom(
              minimumSize: Size(200, 100),
            ),
          ),
          ElevatedButton(
            onPressed: () {
              _showDialog(context, 'O');
            },
            child: const Text(
              "O",
              style: TextStyle(
                fontSize: 40,
                fontWeight: FontWeight.bold,
                color: Colors.black,
                fontFamily: 'Inspiration',
              ),
            ),
            style: ElevatedButton.styleFrom(
              minimumSize: Size(200, 100),
            ),
          ),
        ],
      ),
    );
  }

  void _showDialog(BuildContext context, String option) {
    showDialog(
      context: context,
      builder: (context) {
        return StatefulBuilder(
          builder: (context, setStateForDialog) {
            return Dialog(
              backgroundColor: Colors.white,
              shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(20.0)),
              ),
              child: Container(
                width: 700,
                height: 740,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                  image: DecorationImage(
                    image: backgroundImageGameThree,
                    fit: BoxFit.cover,
                  ),
                ),
                padding: const EdgeInsets.all(20.0),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TicTacToeWidget(selectedOption: option),
                  ],
                ),
              ),
            );
          },
        );
      },
    );
  }
}
