import React from 'react'
import { View, StyleSheet } from 'react-native'
import Field from './Field'

export default props => {
  const rows = props.board.map((row, ri) => {
    const columns = row.map((field, fi) => {
      return <Field {...field} key={fi}
                onOpen={() => props.onOpenField(ri, fi)}
                onSelect={() => props.onSelectField(ri, fi)} />
    })

    return <View key={ri}
                 style={{flexDirection: 'row'}}>{columns}</View>
  })

  return <View style={styles.container}>{rows}</View>
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#EEE'
  }
})