import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:flutter/material.dart';
import '../../../constants/text_strings.dart';
import '../../../home.dart';

class IndexFinderSearchingWidget extends StatefulWidget {
  const IndexFinderSearchingWidget({super.key});

  @override
  State<IndexFinderSearchingWidget> createState() => _IndexFinderSearchingWidgetState();
}

class _IndexFinderSearchingWidgetState extends State<IndexFinderSearchingWidget> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  int? _selectedRole;
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
          fontFamily: inspirationRegular,
        ),
      ),
    );
  }

  Widget generateValue(){
    return Container(
        child: Row(
          children: [
            Text(
              "Value: ",
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
                fontFamily: inspirationRegular,
                fontWeight: FontWeight.bold,
              ),
            ),
            SizedBox(width: 10),
            Text("Generate Value",
              style: TextStyle(
                color: Colors.white, fontSize: 24,
                fontWeight: FontWeight.bold,
                fontFamily: inspirationRegular,
              ),
            ),
          ],
        )
    );

  }

  Widget answer(){
    return Container(
      child: Row(
        children: [
          Container(
            padding: EdgeInsets.all(5),
            child: Column(
              children: [
                roleRadioButton(0, "Answer1"),
                SizedBox(width: 40),
                roleRadioButton(1, "Answer2"),
              ],
            ),
          ),
          Container(
            padding: EdgeInsets.all(5),
            child: Column(
              children: [
                roleRadioButton(2, "Answer3"),
                SizedBox(width: 40),
                roleRadioButton(3, "Answer4"),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget roleRadioButton(int value, String title) {
    return Row(
      children: [
        Radio(
          activeColor: Colors.white,
          value: value,
          groupValue: _selectedRole,
          onChanged: (selectedValue) {
            setState(() {
              _selectedRole = selectedValue as int?;
            });
          },
        ),
        SizedBox(width: 7),
        Text(
          title,
          style: TextStyle(
            color: Colors.white,
            fontSize: 24,
            fontWeight: FontWeight.bold,
            fontFamily: inspirationRegular,
          ),
        ),
      ],
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
      btnCancelOnPress: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => Home()),
        );
      },
      btnOkOnPress: () {},
    ).show();
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
