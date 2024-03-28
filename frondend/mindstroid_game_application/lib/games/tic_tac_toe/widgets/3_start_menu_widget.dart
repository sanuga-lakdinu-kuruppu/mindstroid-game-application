import 'package:flutter/material.dart';
import 'package:mindstroid_game_application/games/index_finder_searching/widgets/4_index_finder_searching_widget.dart';
import 'package:mindstroid_game_application/games/index_finder_sorted/widgets/4_index_finder_sorted_widget.dart';
import 'package:mindstroid_game_application/games/tic_tac_toe/widgets/4_pic_side.dart';

import '../../../constants/image_strings.dart';
import '../../../constants/text_strings.dart';

class StartMenuWidgetThree extends StatefulWidget {
  final String player;
  const StartMenuWidgetThree({required this.player});

  @override
  State<StartMenuWidgetThree> createState() => _StartMenuWidgetThreeState();
}

class _StartMenuWidgetThreeState extends State<StartMenuWidgetThree> {

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          heading(),
          SizedBox(height: 20,),
          gameId(),
          SizedBox(height: 20,),
          image(),
          SizedBox(height: 20,),
          playButton(),
        ],
      ),
    );
  }

  Widget heading(){
    return Container(
      child: Row( // Added 'child' here
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          ElevatedButton(
            onPressed: () {
            },
            child: Icon(
              Icons.arrow_back_ios,
              color: Colors.black,
              size: 30,
            ),
            style: ElevatedButton.styleFrom(
              primary: Colors.white,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10),
              ),
              minimumSize: Size(50, 50),
            ),
          ),
          Text(
            "${widget.player}",
            style: TextStyle(
              color: Colors.white,
              fontSize: 32,
              fontWeight: FontWeight.bold,
              fontFamily: inspirationRegular,
            ),
          ),
          ElevatedButton(
            onPressed: () {
            },
            child: const Icon(
              Icons.volume_down,
              color: Colors.black,
              size: 30,
            ),
            style: ElevatedButton.styleFrom(
              primary: Colors.white,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10),
              ),
              minimumSize: Size(50, 50),
            ),
          ),
        ],
      ),
    );
  }

  Widget gameId(){
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Container(
            child: Row(
              children: [
                Text(
                  "User ID: ",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    fontFamily: inspirationRegular,
                  ),
                ),
                Text(
                  "123456",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    fontFamily: inspirationRegular,
                  ),
                ),
              ],
            ),
          ),
          Container(
            child: Row(
              children: [
                Text(
                  "Game ID: ",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    fontFamily: inspirationRegular,
                  ),
                ),
                Text(
                  "123456",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    fontFamily: inspirationRegular,
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget image() {
    return Container(
      child: Container(
        height: MediaQuery.of(context).size.height * 0.40,
        width: MediaQuery.of(context).size.width * 0.20,
        decoration: BoxDecoration(
          image: DecorationImage(
            image: backgroundImageGameFive,
            fit: BoxFit.cover,
          ),
          color: Colors.green,
          borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
        ),
        alignment: Alignment.center,
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
            ],
          ),
        ),
      ),
    );
  }

  Widget playButton(){
    return ElevatedButton(
      onPressed: () {
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
                    padding: const EdgeInsets.all(50.0),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        PicSideWidget(),
                      ],
                    ),
                  ),
                );
              },
            );
          },
        );
      },
      child: const Text(
        "Play",
        style: TextStyle(
          fontSize: 24,
          fontWeight: FontWeight.bold,
          color: Colors.black,
          fontFamily: inspirationRegular,
        ),
      ),
      style: ElevatedButton.styleFrom(
        primary: Colors.white,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        minimumSize: Size(150, 50),
      ),
    );
  }
}
