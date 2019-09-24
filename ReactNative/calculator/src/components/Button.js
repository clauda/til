

import React from 'react';
import { StyleSheet, Text, Dimensions, TouchableHighlight } from 'react-native';

const styles = StyleSheet.create({
  btn: {
    fontSize: 40,
    height: Dimensions.get('window').width / 4,
    width: Dimensions.get('window').width / 4,
    padding: 20,
    backgroundColor: '#f0f0f0',
    textAlign: 'center',
    borderWidth: 1,
    borderColor: '#888',
  },
  operatorBtn: {
    color: '#fff',
    backgroundColor: '#fa8231',
  },
  largeBtn: {
    width: (Dimensions.get('window').width / 4) * 2,
  },
  xLargeBtn: {
    width: (Dimensions.get('window').width / 4) * 3,
  }
})


export default props => {
  const stylesBtn = [styles.btn]
  if (props.double) stylesBtn.push(styles.largeBtn)
  if (props.triple) stylesBtn.push(styles.xLargeBtn)
  if (props.operator) stylesBtn.push(styles.operatorBtn)

  return (
    <TouchableHighlight onPress={props.onClick}>
      <Text style={stylesBtn}>{props.label}</Text>
    </TouchableHighlight>
  )
}