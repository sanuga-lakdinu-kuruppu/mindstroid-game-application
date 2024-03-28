import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:flutter/material.dart';

class IndexFinderSortedWidget extends StatefulWidget {
  const IndexFinderSortedWidget({super.key});

  @override
  State<IndexFinderSortedWidget> createState() => _IndexFinderSortedWidgetState();
}

class _IndexFinderSortedWidgetState extends State<IndexFinderSortedWidget> {

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          text(),
          SizedBox(
            height: 20,
          ),
          generateValue(),
          SizedBox(
            height: 20,
          ),
          answer(),
          SizedBox(
            height: 20,
          ),
          playButton(),
        ],
      ),
    );
  }

  Widget text(){
    return  Container(
      child: Text(
        "We have Find the Index of the value?",
        style: TextStyle(
          color: Colors.white,
          fontSize: 32,
          fontWeight: FontWeight.bold,
          fontFamily: 'Inspiration',
        ),
      ),
    );
  }

  Widget generateValue(){
    return Container(
      child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children:[
            Container(
              child: Column(
                  children: [
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                  ]
              ),
            ),
            Container(
              child: Column(
                  children: [
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                  ]
              ),
            ),
            Container(
              child: Column(
                  children: [
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                  ]
              ),
            ),
            Container(
              child: Column(
                  children: [
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                    Text(
                      "00000",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Inspiration',
                      ),
                    ),
                  ]
              ),
            ),
          ]
      ),
    );
  }

  Widget answer(){
    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Padding(
            padding: const EdgeInsets.all(30),
            child: TextField(
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.white,
                fontFamily: 'Inspiration',
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
              decoration: InputDecoration(
                fillColor: Colors.black,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(30),
            child: TextField(
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.white,
                fontFamily: 'Inspiration',
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
              decoration: InputDecoration(
                fillColor: Colors.black,
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget playButton(){
    return Padding(
      padding: const EdgeInsets.all(30),
      child: ElevatedButton(
        onPressed: () {
          _showDialogAnswerCorrect();
        },
        child: const Text(
          "Play",
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.black,
            fontFamily: 'Inspiration',
          ),
        ),
        style: ElevatedButton.styleFrom(
          primary: Colors.white,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          minimumSize: Size(150, 50),
        ),
      ),
    );
  }

  void _showDialogAnswerCorrect() {
    AwesomeDialog(
      width: 400,
      context: context,
      dialogType: DialogType.success,
      animType: AnimType.bottomSlide,
      title: 'Success',
      desc: 'Dialog description here.....',
      btnCancelOnPress: () {},
      btnOkOnPress: () {},
    )..show();
  }

  void _showDialogAnswerIncorrect() {
    AwesomeDialog(
      width: 400,
      context: context,
      dialogType: DialogType.error,
      animType: AnimType.bottomSlide,
      title: 'Sorry',
      desc: 'Correct Answer is xxxx. Please try again.',
      btnOkOnPress: () {},
    ).show();
  }
}
