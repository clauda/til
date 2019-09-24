/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import { StyleSheet, View } from 'react-native';

import Button from './src/components/Button'
import Display from './src/components/Display'

const initialState = {
  displayValue: '0',
  clearDisplay: false,
  operation: null,
  values: [0, 0],
  current: 0,
}

export default class App extends Component {
  state = { ...initialState }

  adding = n => {
    const clearDisplay = this.state.displayValue === '0'
      || this.state.clearDisplay
  
    if (n === '.' && !clearDisplay 
      && this.state.displayValue.includes('.')) {
      return
    }

    const currentValue = clearDisplay ? '' : this.state.displayValue
    const displayValue = currentValue + n
    this.setState({ displayValue, clearDisplay: false })

    if (n !== '.') {
      const newValue = parseFloat(displayValue)
      const values = [...this.state.values]
      values[this.state.current] = newValue
      this.setState({ values })
    }
  }

  cleaning = () => {
    this.setState({ ...initialState })
  }

  magic = operation => {
    if (this.state.current === 0 ){
      this.setState({ operation, current: 1, clearDisplay: true })
    } else {
      const equals = operation === '='
      const values = [...this.state.values]

      try {
        values[0] = eval(`${values[0]} ${this.state.operation} ${values[1]}`)
      } catch (e) {
        values[0] = this.state.values[0]
      }

      values[1] = 0
      this.setState({
        displayValue: `${values[0]}`,
        operation: equals ? null : operation,
        current: equals ? 0 : 1,
        //clearDisplay: !equals,
        clearDisplay: true,
        values,
      })
    }
  }

  render() {
    return (
      <View style={styles.container}>
        <Display value={this.state.displayValue} />
        
        <View style={styles.buttons}>
          <Button onClick={this.cleaning} label='AC' triple />
          <Button onClick={() => this.magic('/')} label='/' operator />
          <Button onClick={() => this.adding(7)} label='7' />
          <Button onClick={() => this.adding(8)} label='8' />
          <Button onClick={() => this.adding(9)} label='9' />
          <Button onClick={() => this.magic('*')} label='*' operator />
          <Button onClick={() => this.adding(4)} label='4' />
          <Button onClick={() => this.adding(5)} label='5' />
          <Button onClick={() => this.adding(6)} label='6' />
          <Button onClick={() => this.magic('-')} label='-' operator />
          <Button onClick={() => this.adding(1)} label='1' />
          <Button onClick={() => this.adding(2)} label='2' />
          <Button onClick={() => this.adding(3)} label='3' />
          <Button onClick={() => this.magic('+')} label='+' operator />
          <Button onClick={() => this.adding(0)} label='0' double />
          <Button onClick={() => this.magic('.')} label='.' operator />
          <Button onClick={() => this.magic('=')} label='=' operator />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  buttons: {
    flexDirection: 'row',
    flexWrap: 'wrap'
  }
});
